//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package edu.berkeley.cs.jqf.fuzz;
import com.wxj.demo.generateseed;
import edu.berkeley.cs.jqf.examples.js.JavaScriptRLGenerator;
import edu.berkeley.cs.jqf.fuzz.guidance.Guidance;
import edu.berkeley.cs.jqf.fuzz.junit.GuidedFuzzing;
import edu.berkeley.cs.jqf.fuzz.rl.RLGenerator;
import edu.berkeley.cs.jqf.fuzz.rl.RLGuidance;
import edu.berkeley.cs.jqf.fuzz.rl.RLParamParser;
import edu.berkeley.cs.jqf.fuzz.rl.RLParams;
import java.io.File;

public class test {
    public test() {
    }

    public static void main(String[] args) {
        try {
            // RLParams params = paramParser.getParamsFile("/Users/xiayifan/Downloads/rlcheck-master/jqf/configFiles/mavenConfig.json");
            MyGenerator gen = new MyGenerator();

            generateseed gene = new generateseed("/Users/xiayifan/Downloads/js-test-suite-master/testsuite");
            gen.init(gene);
            Guidance guidance = new MyGuidance(gen, "test", null, new File("/Users/xiayifan/Desktop/作业"));

            // Run the Junit test
            //GuidedFuzzing.run("edu.berkeley.cs.jqf.examples.maven.ModelReaderTest", "testWithInputStream", guidance, System.out);
            GuidedFuzzing.run("edu.berkeley.cs.jqf.fuzz.CompilerTest", "testWithInputStream", guidance, System.out);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(2);
        }
    }}
