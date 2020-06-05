package net.ezens.pointnix.notice;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.ezens.pointnix.notice.model.Notice;
import net.ezens.pointnix.payload.NoticeRequest;

@Repository
public interface NoticeMapper {
	int	getNoticeListCount();
	List<Notice> getNoticeList(NoticeRequest request);

}
