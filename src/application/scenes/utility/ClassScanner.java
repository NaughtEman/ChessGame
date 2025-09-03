package application.scenes.utility;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassScanner {

    public static List<Class<?>> getClasses(String packageName) {
        List<Class<?>> classes = new ArrayList<>();
        String path = packageName.replace('.', '/');
        try {
            Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(path);
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                String filePath = resource.getFile();

                if (filePath.contains("!")) { // inside a jar
                    String jarPath = filePath.substring(5, filePath.indexOf("!"));
                    try (JarFile jar = new JarFile(jarPath)) {
                        jar.stream()
                                .filter(e -> e.getName().endsWith(".class"))
                                .filter(e -> e.getName().startsWith(path)) // include subpackages
                                .forEach(e -> addClass(classes, e.getName()));
                    }
                } else { // normal filesystem
                    File dir = new File(resource.getFile());
                    findClassesInDirectory(dir, packageName, classes);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classes;
    }

    private static void findClassesInDirectory(File directory, String packageName, List<Class<?>> classes) {
        if (!directory.exists()) return;
        File[] files = directory.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isDirectory()) {
                // recurse into subpackage
                findClassesInDirectory(file, packageName + "." + file.getName(), classes);
            } else if (file.getName().endsWith(".class")) {
                String className = packageName + '.' + file.getName().replace(".class", "");
                try {
                    classes.add(Class.forName(className));
                } catch (ClassNotFoundException ignored) {}
            }
        }
    }

    private static void addClass(List<Class<?>> classes, String classPath) {
        String className = classPath.replace('/', '.').replace('\\', '.').replace(".class", "");
        try {
            classes.add(Class.forName(className));
        } catch (ClassNotFoundException ignored) {}
    }
}
