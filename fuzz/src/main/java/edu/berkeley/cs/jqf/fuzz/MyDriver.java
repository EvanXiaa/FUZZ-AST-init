package edu.berkeley.cs.jqf.fuzz;
import com.wxj.demo.generateseed;
import edu.berkeley.cs.jqf.fuzz.guidance.Guidance;
import edu.berkeley.cs.jqf.fuzz.junit.GuidedFuzzing;


import java.io.File;
import java.lang.reflect.Constructor;


/**
 * Created by clemieux on 6/17/19.
 */
public class MyDriver {

    public static void main(String[] args) {

        if (args.length < 4){
            System.err.println("Usage: java " + MyDriver.class + " TEST_CLASS TEST_METHOD GENERATOR_CLASS CONFIG_FILE [OUTPUT_DIR]");
            System.exit(1);
        }

        String testClassName  = args[0];
        String testMethodName = args[1];
        String genClassName  = args[2];
        String configurationFileName = args[3];
        String outputDirectoryName = args.length > 4 ? args[4] : "fuzz-results";

        File outputDirectory = new File(outputDirectoryName);
        try {
            // Load the generator
            Class<?> clazz = Class.forName(genClassName);
            System.out.println(clazz.toString());
            Constructor<?> ctor = clazz.getConstructor();
            System.out.println(ctor);
            MyGenerator gen = (MyGenerator) ctor.newInstance();

            generateseed gene = new generateseed("/Users/xiayifan/Downloads/js-test-suite-master/testsuite");
            gen.init(gene);


            // Load the guidance
            String title = testClassName+"#"+testMethodName + " (" + genClassName + ")";
            Guidance guidance = new MyGuidance(gen, title, null, outputDirectory);

            // Run the Junit test
            GuidedFuzzing.run(testClassName, testMethodName, guidance, System.out);

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(2);
        }




    }
}
