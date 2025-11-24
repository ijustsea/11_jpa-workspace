package jpabook.jpashop.study;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;

public class GenericWrapper {
    /*
        GenericWrapper : api 응답시 단순히 List<Member> 반환하면 나중에 다른 필드 넣기어려움
        이름 반환 시
        [
            {"name": "차은우"},
            {"name": "박정민"}
        ]
        요구사항 변경되어서 만약 count 추가해야 한다면?
        [
            "count" : 2,
            "data" : [
                        {"name": "차은우"},
                        {"name": "박정민"}
                    ]
        ]
        이런식으로 응답전체를 감싸는 wrapper 필요한데 그게 바로 Result<T>
        T : 모든 타입이 가능함
        즉, API 응답 유연하게 감싸기 위한 Wrapper class
    */

    @GetMapping("/test/result")
    public Result<String> testResult() {
        return new Result<>("Hello");
    }

    @AllArgsConstructor
    @Data
    public static class Result<T> {
        private T data;
    }
}
