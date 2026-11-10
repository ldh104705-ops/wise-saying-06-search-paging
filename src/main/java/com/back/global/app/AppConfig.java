package com.back.global.app;

/**
 * 실행 모드 설정. 테스트에서는 샘플 데이터를 끌 수 있다.
 */
public class AppConfig {
    private static boolean sampleDataEnabled = true;

    public static boolean isSampleDataEnabled() {
        return sampleDataEnabled;
    }

    public static void setSampleDataEnabled(boolean sampleDataEnabled) {
        AppConfig.sampleDataEnabled = sampleDataEnabled;
    }
}
