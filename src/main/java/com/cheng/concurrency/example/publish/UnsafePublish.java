package publish;

import com.cheng.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @Author :cheng
 * @Description:
 * @Date: created in 21:39 2018/6/22
 * @Reference:
 */
@Slf4j
@NotThreadSafe
public class UnsafePublish {
    private String[] states = {"a", "b", "c"};
    public String[] getStates() {

        return states;
    }

    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();
        log.info("{}", Arrays.toString(unsafePublish.getStates()));

        unsafePublish.getStates()[0] = "e";
        UnsafePublish unsafePublish1 = new UnsafePublish();
        log.info("{}", Arrays.toString(unsafePublish.getStates()));
    }
}