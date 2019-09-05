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
        return new MainFrameDialogProvider(master);
    }

    @Bean
    public MainFrameDialogProvider nestedDialogProvider(SomeNestedUIView nestedUIView) {
        return new MainFrameDialogProvider(nestedUIView);
    }

    public static class MainFrameDialogProvider {
        private final Object component;

        public MainFrameDialogProvider(Object component) {
            this.component = component;
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
