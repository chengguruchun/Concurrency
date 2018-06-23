package publish;

import com.cheng.concurrency.annoations.NotRecommend;
import com.cheng.concurrency.annoations.NotThreadSafe;
import com.cheng.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author :cheng
 * @Description:
 * @Date: created in 21:35 2018/6/22
 * @Reference:
 */
@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {
    private int thisCanBeEscape = 0;
    public Escape() {
        new InnerClass();
    }

    private class InnerClass{
        public InnerClass() {
            log.info("{}", Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}