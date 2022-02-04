package mapperInterface;

import java.util.List;

import vo.AskVO;
import vo.NoticeVO;
import vo.PageVO;

public interface AskMapper {
	
	// ** PageList1.
		int totalRowCount();
		List<AskVO> pageList(PageVO<AskVO> pvo); 
	
	// **  CRUD
	List<AskVO> selectList(); //selectList
	AskVO selectOne(AskVO vo); //selectOne
	int insert(AskVO vo); //insert
	int update(AskVO vo); //update
	int delete(AskVO vo); //delete
	
	// ** 답글입력
	int ansinsert(AskVO vo);
	
} //interface
