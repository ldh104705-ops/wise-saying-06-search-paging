package com.back.domain.wiseSaying.repository;

import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.standard.dto.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 데이터 생성·조회·수정·삭제 담당. 메모리(리스트) 저장소.
 * Scanner·콘솔 출력 사용 금지.
 */
public class WiseSayingRepository {
    private final List<WiseSaying> wiseSayings = new ArrayList<>();
    private int lastId = 0;

    public WiseSaying save(WiseSaying wiseSaying) {
        if (wiseSaying.isNew()) {
            wiseSaying.setId(++lastId);
            wiseSayings.add(wiseSaying);
        }

        return wiseSaying;
    }

    public List<WiseSaying> findAll() {
        return wiseSayings.reversed();
    }

    public Page<WiseSaying> findForList(String keywordType, String keyword, int page, int pageSize) {
        List<WiseSaying> filtered = wiseSayings.reversed()
                .stream()
                .filter(wiseSaying -> matches(wiseSaying, keywordType, keyword))
                .toList();

        List<WiseSaying> content = filtered.stream()
                .skip((long) (page - 1) * pageSize)
                .limit(pageSize)
                .toList();

        return new Page<>(content, filtered.size(), page, pageSize);
    }

    private boolean matches(WiseSaying wiseSaying, String keywordType, String keyword) {
        if (keyword == null || keyword.isBlank()) return true;

        return switch (keywordType) {
            case "content" -> wiseSaying.getContent().contains(keyword);
            case "author" -> wiseSaying.getAuthor().contains(keyword);
            default -> wiseSaying.getContent().contains(keyword) || wiseSaying.getAuthor().contains(keyword);
        };
    }

    public int count() {
        return wiseSayings.size();
    }

    public Optional<WiseSaying> findById(int id) {
        return wiseSayings.stream()
                .filter(wiseSaying -> wiseSaying.getId() == id)
                .findFirst();
    }

    public boolean delete(int id) {
        return wiseSayings.removeIf(wiseSaying -> wiseSaying.getId() == id);
    }
}
