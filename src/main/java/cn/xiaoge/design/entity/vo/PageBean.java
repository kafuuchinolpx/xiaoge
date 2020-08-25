package cn.xiaoge.design.entity.vo;

import lombok.Data;
import java.util.Collection;
import java.io.Serializable;

@Data
public class PageBean<T> implements Serializable {

    private PageBean() {
    }

    private Collection<T> content;

    private Integer page;

    private Integer size;

    private Long totalCount;

    private Long pageCount;

    private String order;

    private String searchKey;

    private PageBean(Collection<T> content, long totalCount, int page, Integer size, String order, String searchKey) {
        this.content = content;
        this.totalCount = totalCount;
        this.size = size;
        this.page = page;
        this.order = order;
        this.searchKey = searchKey;
        this.pageCount = totalCount % size == 0 ? (totalCount / size) : (totalCount / size + 1);
    }

    public static PageBean of(Collection content, long totalCount, int page, Integer size, String order, String searchKey) {
        return new PageBean(content, totalCount, page, size, order, searchKey);
    }

    public static PageBean of(Collection content, long totalCount, int page, Integer size) {
        return new PageBean(content, totalCount, page, size, "", "");
    }

    public static PageBean of(org.springframework.data.domain.Page all, String order, String searchKey) {
        return new PageBean(all.getContent(), all.getTotalElements(), all.getNumber() + 1, all.getSize(), order, searchKey);
    }

    public static PageBean of(org.springframework.data.domain.Page all) {
        return new PageBean(all.getContent(), all.getTotalElements(), all.getNumber() + 1, all.getSize(), "", "");
    }

}
