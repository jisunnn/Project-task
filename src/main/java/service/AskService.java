package service;

import java.util.List;

import vo.AskVO;
import vo.NoticeVO;
import vo.PageVO;

public interface AskService {

	// ** PageList1.
	PageVO<AskVO> pageList(PageVO<AskVO> pvo); // pageList 1 
	
	// **  CRUD
	List<AskVO> selectList(); //selectList
	
	AskVO selectOne(AskVO vo); //selectOne
	
	int insert(AskVO vo); //insert
	
	int update(AskVO vo); //update
	
	int delete(AskVO vo); //delete

	// ** 답글입력
	int ansinsert(AskVO vo);
	
	
	

}