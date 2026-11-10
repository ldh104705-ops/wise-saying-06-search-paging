package com.back.global.app;

import com.back.domain.system.controller.SystemController;
import com.back.domain.wiseSaying.controller.WiseSayingController;
import com.back.domain.wiseSaying.repository.WiseSayingRepository;
import com.back.domain.wiseSaying.service.WiseSayingService;

import java.util.Scanner;

/**
 * 공유 객체를 한 곳에서 하나씩만 만든다.
 */
public class AppContext {
    public static Scanner scanner;
    public static WiseSayingRepository wiseSayingRepository;
    public static WiseSayingService wiseSayingService;
    public static SystemController systemController;
    public static WiseSayingController wiseSayingController;

    public static void renew(Scanner scanner) {
        AppContext.scanner = scanner;
        wiseSayingRepository = new WiseSayingRepository();
        wiseSayingService = new WiseSayingService(wiseSayingRepository);
        systemController = new SystemController();
        wiseSayingController = new WiseSayingController(scanner, wiseSayingService);
    }
}
