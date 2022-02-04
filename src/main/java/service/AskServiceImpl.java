package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mapperInterface.AskMapper;
import vo.AskVO;
import vo.NoticeVO;
import vo.PageVO;

@Service
public class AskServiceImpl implements AskService {
	
	@Autowired
	AskMapper dao;
	
	// ** PageList1.
	@Override
	public PageVO<AskVO> pageList(PageVO<AskVO> pvo) {
		pvo.setTotalRowCount(dao.totalRowCount());
		pvo.setList(dao.pageList(pvo));
		return pvo;
	} //pageList
	
	
	@Override
	public List<AskVO> selectList() {
		return dao.selectList();
	} //selectList
	
	@Override
	public AskVO selectOne(AskVO vo) {
		return dao.selectOne(vo);
	} //selectOne

	@Override
	public int insert(AskVO vo) {
		return dao.insert(vo) ;
	} //insert
	@Override
	public int update(AskVO vo) {
		return dao.update(vo) ;
	} //update
	@Override
	public int delete(AskVO vo) {
		return dao.delete(vo) ;
	} //delete
		
	// ** 답글입력
	@Override
	public int ansinsert(AskVO vo) {
		return dao.ansinsert(vo);
	}
	
} //class
