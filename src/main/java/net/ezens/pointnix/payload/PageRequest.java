package net.ezens.pointnix.payload;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;

import lombok.Getter;

@Getter
public class PageRequest {
	
	private int size = 10;
	private int page = 1;
	private String sort;
	
	private static int MAX_PAGE_SIZE = 5;
	private static int DEFAULT_SIZE = 10;
	private static int MAX_SIZE = 100;
	
	public void setPage(int page) {
		this.page = page <= 0 ? 1 : page;
	}
	public void setSize(int size) {
		this.size = size > MAX_SIZE ? DEFAULT_SIZE : size;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	
	public org.springframework.data.domain.PageRequest of() {
		if(StringUtils.isNotEmpty(sort)) {
			return org.springframework.data.domain.PageRequest.of(page-1, size, Sort.Direction.DESC, sort);
		} else {
			return org.springframework.data.domain.PageRequest.of(page-1, size);
		}
	}

}
