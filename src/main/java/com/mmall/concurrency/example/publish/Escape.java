package com.mmall.concurrency.example.publish;

import com.mmall.concurrency.annotations.NotRecommend;
import com.mmall.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NotThreadSafe
@NotRecommend
/**
 * 对象逸出：一种错误的发布。当一个对象还没有构造完成时，就使它被其他线程所见
 *
 * 在对象还没完成构造之前不可以发布
 *
 */
public class Escape {
    private int thisCanBeEscape = 0;

    public Escape(){
        new InnerClass();
    }

    private class InnerClass{
        public InnerClass(){
            log.info("{}",Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args){
        new Escape();
    }

}
