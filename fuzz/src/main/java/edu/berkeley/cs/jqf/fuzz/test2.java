//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package edu.berkeley.cs.jqf.fuzz;

import edu.berkeley.cs.jqf.examples.js.JavaScriptRLGenerator;
import edu.berkeley.cs.jqf.fuzz.guidance.Guidance;
import edu.berkeley.cs.jqf.fuzz.junit.GuidedFuzzing;
import edu.berkeley.cs.jqf.fuzz.rl.RLGenerator;
import edu.berkeley.cs.jqf.fuzz.rl.RLGuidance;
import edu.berkeley.cs.jqf.fuzz.rl.RLParamParser;
import edu.berkeley.cs.jqf.fuzz.rl.RLParams;
import java.io.File;

public class test2 {
    public test2() {
    }

    public static void main(String[] args) {
        try {RLParamParser paramParser = new RLParamParser();
            RLParams params = paramParser.getParamsFile("/Users/xiayifan/Downloads/rlcheck-master/jqf/configFiles/closureConfig.json");
            // RLParams params = paramParser.getParamsFile("/Users/xiayifan/Downloads/rlcheck-master/jqf/configFiles/mavenConfig.json");
            RLGenerator gen = new JavaScriptRLGenerator();
            gen.init(params);
            Guidance guidance = new RLGuidance(gen, "test", null, new File("/Users/xiayifan/Desktop/作业"));

            // Run the Junit test
            //GuidedFuzzing.run("edu.berkeley.cs.jqf.examples.maven.ModelReaderTest", "testWithInputStream", guidance, System.out);
             GuidedFuzzing.run("edu.berkeley.cs.jqf.fuzz.CompilerTest", "testWithInputStream", guidance, System.out);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(2);
        }
    }}
