package matlabProxy;

import matlabcontrol.MatlabConnectionException;
import matlabcontrol.MatlabInvocationException;
import matlabcontrol.MatlabProxy;
import matlabcontrol.MatlabProxyFactory;
import matlabcontrol.extensions.MatlabNumericArray;
import matlabcontrol.extensions.MatlabTypeConverter;

import java.util.List;

/**
 * Created by Julien Hatin on 03/11/15.
 */
public class MatlabSingleton {

    private static MatlabSingleton singleton;

    private MatlabProxy proxy;
    private MatlabTypeConverter processor;

    private MatlabSingleton() {

    }

    public static MatlabSingleton getInstance() throws MatlabConnectionException {
        if (null == singleton) {
            singleton = new MatlabSingleton();
            MatlabProxyFactory factory = new MatlabProxyFactory();
            singleton.proxy = factory.getProxy();
            singleton.processor = new MatlabTypeConverter(singleton.proxy);
        }
        return singleton;
    }

    public void setNumericArray(String name, double[][] data) throws MatlabInvocationException {
        processor.setNumericArray(name, new MatlabNumericArray(data, null));
    }

    public void setNumericArray(String name, List<List<Double>> data) throws MatlabInvocationException {

        if (data.size() < 1 || data.get(0).size() < 1) {
            //setNumericArray(name, new double[1][1]);
        } else {

            double[][] array = new double[data.size()][data.get(0).size()];
            int indexX = 0;
            int indexY = 0;

            for (List<Double> x : data) {
                for (Double y : x) {
                    array[indexX][indexY] = y;
                    indexY++;
                }
                indexX++;
                indexY = 0;
            }
            setNumericArray(name, array);
        }
    }


    public void eval(String command) throws MatlabInvocationException {
        proxy.eval(command);
    }

    public void disconnect() {
        proxy.disconnect();
        singleton = null;
    }

}
