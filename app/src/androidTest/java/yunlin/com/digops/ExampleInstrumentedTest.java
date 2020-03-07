package yunlin.com.digops;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.brc.idauth.bean.DeviceBean;
import com.brc.idauth.ui.mine.MineModle;
import com.google.gson.Gson;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        Context appContext = InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void testUserLogin() {


        DeviceBean appDeviceBean = new MineModle().getAppDeviceBean();


        String deviceStr = new Gson().toJson(appDeviceBean);
        System.out.println("lixiaodaoaaa" + deviceStr);

    }

}
