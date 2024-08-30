package bo.custom.impl;

import java.util.ArrayList;
import java.util.List;

import bo.custom.BranchBO;
import dao.DAOFactory;
import dao.custom.BranchDAO;
import dto.BranchDTO;
import entity.Branch;

public class BranchBOImpl implements BranchBO {
    BranchDAO branchDAO = (BranchDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.BRANCH);

    @Override
    public boolean saveBranch(BranchDTO dto) {
        return branchDAO.save(new Branch(dto.getAddress(), dto.getOpenedDate()));
    }

    @Override
    public List<BranchDTO> getAllBranch() {
        List<BranchDTO> branchDTOS = new ArrayList<>();
        List<Branch> branches = branchDAO.getAll();
        for (Branch branch : branches) {
            branchDTOS.add(new BranchDTO(branch.getId(), branch.getAddress(), branch.getOpenedDate()));
        }
        return branchDTOS;
    }

    @Override
    public boolean updateBranch(BranchDTO dto) {
        return branchDAO.update(new Branch(dto.getId(), dto.getAddress(), dto.getOpenedDate()));
    }

    @Override
    public boolean deleteBranch(long id) {
        return branchDAO.delete(id);
    }
}
