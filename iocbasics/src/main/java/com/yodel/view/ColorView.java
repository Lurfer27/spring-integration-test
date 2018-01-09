package com.yodel.view;

import com.yodel.model.ColorEnum;
import org.springframework.context.ApplicationContext;

public class ColorView {

    public static void showColors(ApplicationContext ctx) {
        System.out.println("Show Colors - Always the same:");
        for (int i = 0; i < 5; i++) {
            System.out.println(ctx.getBean("randomColorAlwaysTheSame", ColorEnum.class));
        }

        System.out.println("Show Colors - Always different:");
        for (int i = 0; i < 5; i++) {
            System.out.println(ctx.getBean("randomColorAlwaysDifferent", ColorEnum.class));
        }
    }
}
