package net.ezens.pointnix.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.ezens.pointnix.notice.model.Notice;

@Service
public class NoticeService {
	
	@Autowired
	NoticeMapper mapper;
	
	@Autowired
	NoticeRepository repository;
	
	
	public Page<Notice> noticeList(Pageable pageable) {
		return repository.findAll(pageable);
	}
}
