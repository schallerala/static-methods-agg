package ch.alains.generator.config;

import org.hamcrest.generator.*;

import java.io.File;
import java.io.FileWriter;

public class RecursiveConfigurator {

    private final SugarConfiguration sugarConfiguration;
    private final QDox qdox;

    public RecursiveConfigurator(SugarConfiguration sugarConfiguration) {
        this.sugarConfiguration = sugarConfiguration;
        qdox = new QDox();
    }

    public void addSourceDir(File sourceDir) {
        qdox.addSourceTree(sourceDir);
    }

    public void loadAllClasses() {
        this.qdox.getClasses().forEach(c -> {
            sugarConfiguration.addFactoryMethods(new QDoxFactoryReader(qdox, c.getFullyQualifiedName()));
        });
    }

    public static void main(String[] args) throws Exception {
        System.err.println("Hamcrest Sugar Generator");
        System.err.println("========================");

        if (args.length != 3) {
            System.err.println("Args: source-dir generated-class output-dir");
            System.err.println("");
            System.err.println("    source-dir  : Path to Java source containing matchers to generate sugar for.");
            System.err.println("                  May contain multiple paths, separated by commas.");
            System.err.println("                  e.g. src/java,src/more-java");
            System.err.println("");
            System.err.println("generated-class : Full name of class to generate.");
            System.err.println("                  e.g. org.myproject.MyMatchers");
            System.err.println("");
            System.err.println("     output-dir : Where to output generated code (package subdirs will be");
            System.err.println("                  automatically created).");
            System.err.println("                  e.g. build/generated-code");
            System.exit(-1);
        }

        String srcDirs = args[0];
        String fullClassName = args[1];
        File outputDir = new File(args[2]);

        String fileName = fullClassName.replace('.', File.separatorChar) + ".java";
        int dotIndex = fullClassName.lastIndexOf(".");
        String packageName = dotIndex == -1 ? "" : fullClassName.substring(0, dotIndex);
        String shortClassName = fullClassName.substring(dotIndex + 1);

        if (!outputDir.isDirectory() && !outputDir.mkdirs()) {
            System.err.println("Unable to create directory not : " + outputDir.getAbsolutePath());
            System.exit(-1);
        }

        File outputFile = new File(outputDir, fileName);
        outputFile.getParentFile().mkdirs();

        SugarGenerator sugarGenerator = new SugarGenerator();
        try {
            sugarGenerator.addWriter(new HamcrestFactoryWriter(
                    packageName, shortClassName, new FileWriter(outputFile)));
            sugarGenerator.addWriter(new QuickReferenceWriter(System.out));

            RecursiveConfigurator configurator = new RecursiveConfigurator(sugarGenerator);

            if (srcDirs.trim().length() > 0) {
                for (String srcDir : srcDirs.split(",")) {
                    configurator.addSourceDir(new File(srcDir));
                }
            }
            configurator.loadAllClasses();

            System.out.println("Generating " + fullClassName);
            sugarGenerator.generate();
        } finally {
            sugarGenerator.close();
        }
    }
}
