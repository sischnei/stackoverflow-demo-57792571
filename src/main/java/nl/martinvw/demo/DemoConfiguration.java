package nl.martinvw.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class DemoConfiguration {

    @Bean
    public MainFrameDialogProvider mainDialogProvider(MainUIFrame master) {
        return new MainFrameDialogProvider(master, "mainDialogProvider");
    }

    @Bean
    public MainFrameDialogProvider nestedDialogProvider(MainUIFrame master) {
        return new MainFrameDialogProvider(master, "nestedDialogProvider");
    }

    public static class MainFrameDialogProvider {
        private final MainUIFrame master;
        private final String nestedDialogProvider;

        public MainFrameDialogProvider(MainUIFrame master, String nestedDialogProvider) {
            this.master = master;
            this.nestedDialogProvider = nestedDialogProvider;
        }
    }

    @Component
    public static class SomeNestedUIView {
        private final MainFrameDialogProvider dialogProvider;

        public SomeNestedUIView(@Qualifier("nestedDialogProvider") MainFrameDialogProvider dialogProvider) {
            this.dialogProvider = dialogProvider;
        }
    }

    @Component
    public static class MainUIFrame {
        private SomeNestedUIView compA;
        private MainFrameDialogProvider mainFrameDialogProvider;

        @Autowired
        public void setCompA(SomeNestedUIView compA) {
            this.compA = compA;
        }

        @Autowired
        public void setMainFrameDialogProvider(@Qualifier("mainDialogProvider") MainFrameDialogProvider mainFrameDialogProvider) {
            this.mainFrameDialogProvider = mainFrameDialogProvider;
        }
    }
}
