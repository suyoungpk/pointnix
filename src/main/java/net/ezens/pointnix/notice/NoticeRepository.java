package net.ezens.pointnix.notice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.ezens.pointnix.notice.model.Notice;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {

}
