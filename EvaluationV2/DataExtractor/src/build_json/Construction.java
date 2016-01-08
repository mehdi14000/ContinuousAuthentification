package build_json;

import com.google.gson.Gson;

import matlabcontrol.MatlabConnectionException;
import matlabcontrol.MatlabInvocationException;
import matlabcontrol.MatlabProxy;
import matlabcontrol.MatlabProxyFactory;
import matlabcontrol.extensions.MatlabNumericArray;
import matlabcontrol.extensions.MatlabTypeConverter;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Julien Hatin on 29/10/15.
 */
public class Construction {

    public static void main(String[] args) throws MatlabConnectionException, MatlabInvocationException, InterruptedException {

        ArrayList<Integer> dataCollectionSubjects = new ArrayList<>();
        for (int i = 1; i <= 37; i++) {
            dataCollectionSubjects.add(i);
        }
//
//        ArrayList<Integer> mitSubjects = new ArrayList<>();
//        mitSubjects.add(100);
//        mitSubjects.add(97);
//        mitSubjects.add(96);
//        mitSubjects.add(93);
//        mitSubjects.add(71);
//        mitSubjects.add(70);
//        mitSubjects.add(67);
//        mitSubjects.add(58);
//        mitSubjects.add(53);
//        mitSubjects.add(52);
//        mitSubjects.add(51);
//        mitSubjects.add(40);
//        mitSubjects.add(38);
//        mitSubjects.add(37);
//        mitSubjects.add(36);
//        mitSubjects.add(35);
//        mitSubjects.add(34);
//        mitSubjects.add(33);
//        mitSubjects.add(32);
//        mitSubjects.add(31);
//        mitSubjects.add(26);
//        mitSubjects.add(25);
//        mitSubjects.add(23);
//        mitSubjects.add(21);
//        mitSubjects.add(20);
//        mitSubjects.add(16);
//        mitSubjects.add(15);
//        mitSubjects.add(12);
//        mitSubjects.add(11);
//        mitSubjects.add(8);
//        mitSubjects.add(5);

        ArrayList<Integer> mitSubjects = new ArrayList<>();
        mitSubjects.add(4);
        mitSubjects.add(5);
        mitSubjects.add(6);
        mitSubjects.add(7);
        mitSubjects.add(8);
        mitSubjects.add(9);
        mitSubjects.add(10);
        mitSubjects.add(11);
        mitSubjects.add(12);
        mitSubjects.add(13);
        mitSubjects.add(14);
        mitSubjects.add(15);
        mitSubjects.add(16);
        mitSubjects.add(17);
        mitSubjects.add(20);
        mitSubjects.add(21);
        mitSubjects.add(22);
        mitSubjects.add(23);
        mitSubjects.add(25);
        mitSubjects.add(26);
        mitSubjects.add(27);
        mitSubjects.add(28);
        mitSubjects.add(29);
        mitSubjects.add(31);
        mitSubjects.add(32);
        mitSubjects.add(33);
        mitSubjects.add(34);
        mitSubjects.add(35);
        mitSubjects.add(36);
        mitSubjects.add(37);
        mitSubjects.add(38);
        mitSubjects.add(39);
        mitSubjects.add(40);
        mitSubjects.add(42);
        mitSubjects.add(44);
        mitSubjects.add(46);
        mitSubjects.add(49);
        mitSubjects.add(50);
        mitSubjects.add(51);
        mitSubjects.add(52);
        mitSubjects.add(53);
        mitSubjects.add(54);
        mitSubjects.add(55);
        mitSubjects.add(56);
        mitSubjects.add(57);
        mitSubjects.add(58);
        mitSubjects.add(60);
        mitSubjects.add(61);
        mitSubjects.add(62);
        mitSubjects.add(63);
        mitSubjects.add(65);
        mitSubjects.add(67);
        mitSubjects.add(68);
        mitSubjects.add(69);
        mitSubjects.add(70);
        mitSubjects.add(71);
        mitSubjects.add(73);
        mitSubjects.add(74);
        mitSubjects.add(75);
        mitSubjects.add(76);
        mitSubjects.add(77);
        mitSubjects.add(78);
        mitSubjects.add(79);
        mitSubjects.add(81);
        mitSubjects.add(82);
        mitSubjects.add(83);
        mitSubjects.add(84);
        mitSubjects.add(86);
        mitSubjects.add(89);
        mitSubjects.add(90);
        mitSubjects.add(91);
        mitSubjects.add(93);
        mitSubjects.add(94);
        mitSubjects.add(95);
        mitSubjects.add(96);
        mitSubjects.add(97);
        mitSubjects.add(99);
        mitSubjects.add(101);
        mitSubjects.add(102);
        mitSubjects.add(103);

         buildDatacollection(dataCollectionSubjects);
        //buildMIT(mitSubjects);
        //Computer computer = new Computer();
        //computer.computeTrustCurve(new File("features_2"), mitSubjects, new BasicTrustFunction(-0.1, 0.07));
        //computer.computeFRR(new File("features_2"), mitSubjects, new BasicTrustFunction(-0.1, 0.07));
        //computer.computeDetectionTime(new File("features_2"), mitSubjects, new BasicTrustFunction(-0.1, 0.07));

    }

    public static void buildDatacollection(ArrayList<Integer> subjects) throws MatlabInvocationException, MatlabConnectionException {
        MatlabProxyFactory factory = new MatlabProxyFactory();
        MatlabProxy proxy = factory.getProxy();
        MatlabTypeConverter processor = new MatlabTypeConverter(proxy);

        Gson gson = new Gson();
        File f = new File("features_3");
        //File f = new File("features_datacollection_similar");

        HashMap<Integer, double[][]> matlabFeatures = new HashMap<>();
        for (int subjectNumber : subjects) {
            System.out.println(subjectNumber);
            proxy.eval("m = app_building_3(" + subjectNumber + ");");
            //Get the array from MATLAB
            processor = new MatlabTypeConverter(proxy);
            MatlabNumericArray array = processor.getNumericArray("m");
            matlabFeatures.put(subjectNumber, array.getRealArray2D());
        }

        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f)));
            pw.println(gson.toJson(matlabFeatures));
            pw.close();
        } catch (IOException exception) {
            System.out.println("Erreur lors de la lecture : " + exception.getMessage());
        }
        System.out.println("Features created ");
        proxy.disconnect();
    }

    public static void buildMIT(ArrayList<Integer> subjects) throws MatlabInvocationException, MatlabConnectionException {

        //File f = new File("features_2");
        //File f = new File("features_MIT_long");
        File f = new File("features_MIT_similar");
        Gson gson = new Gson();

        System.out.println("Loading matlab");

        MatlabProxyFactory factory = new MatlabProxyFactory();
        MatlabProxy proxy = factory.getProxy();
        MatlabTypeConverter processor = new MatlabTypeConverter(proxy);

        proxy.eval("loadMIT");

        HashMap<Integer, double[][]> matlabFeatures = new HashMap<>();
        for (int subjectNumber : subjects) {
            System.out.println(subjectNumber);
            proxy.eval("m = app_building_2(s(" + subjectNumber + "))");
            //Get the array from MATLAB
            processor = new MatlabTypeConverter(proxy);
            MatlabNumericArray array = processor.getNumericArray("m");
            matlabFeatures.put(subjectNumber, array.getRealArray2D());
        }

        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f)));
            String jsonString = gson.toJson(matlabFeatures);
            pw.println(jsonString);
            pw.close();
        } catch (IOException exception) {
            System.out.println("Erreur lors de la lecture : " + exception.getMessage());
        }
        System.out.println("Features created ");

        proxy.disconnect();
    }
}