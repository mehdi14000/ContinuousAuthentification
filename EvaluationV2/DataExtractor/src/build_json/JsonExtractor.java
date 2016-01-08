package build_json;

import com.google.gson.Gson;
import engine.discreteValues.StringDiscreteValue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Julien Hatin on 09/12/15.
 */
public class JsonExtractor {

    private static JsonExtractor jsonExtractor = new JsonExtractor();

    private JsonExtractor() {
    }

    public static JsonExtractor getInstance() {
        return jsonExtractor;
    }

    /****
     * Instance
     ****/

    public HashMap<Integer, List<Feature>> extractFeatures(File jsonFile, List<Integer> subjects) {
        Gson gson = new Gson();
        HashMap<String, ArrayList<ArrayList<Double>>> userFeatures = null;
        HashMap<Integer, List<Feature>> features = new HashMap<>();
        try {
            userFeatures = gson.fromJson(new FileReader(jsonFile), new HashMap<String, double[][]>().getClass());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (Integer subject : subjects) {
            ArrayList<ArrayList<Double>> javaArray = userFeatures.get(Integer.toString(subject));
            List<Feature> featureList = new ArrayList<>();
            int index = 0;
            while (index < javaArray.size()) {
                String key = javaArray.get(index).get(0).toString() + javaArray.get(index).get(1).toString();
                featureList.add(new Feature(javaArray.get(index).get(2), new StringDiscreteValue(key)));
                index++;
            }
            features.put(subject, featureList);
        }
        return features;
    }


}
