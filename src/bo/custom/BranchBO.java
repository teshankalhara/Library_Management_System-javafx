package bo.custom;

import java.util.List;

import bo.SupperBO;
import dto.BranchDTO;

public interface BranchBO extends SupperBO {
    boolean saveBranch(BranchDTO dto);

    List<BranchDTO> getAllBranch();

    boolean updateBranch(BranchDTO dto);

    boolean deleteBranch(long id);
}
