package dto;

import java.time.LocalDate;

import entity.Branch;

public class BranchDTO {
    private long id;
    private String address;
    private LocalDate openedDate;

    public BranchDTO() {
    }

    public BranchDTO(String address, LocalDate openedDate) {
        this.address = address;
        this.openedDate = openedDate;
    }

    public BranchDTO(long id, String address, LocalDate openedDate) {
        this.id = id;
        this.address = address;
        this.openedDate = openedDate;
    }

    public long getId() {
        return id;
    }

    public Branch toEntity(BranchDTO dto) {
        return new Branch(dto.getId(), dto.getAddress(), dto.getOpenedDate());
    }

    public Branch ToEntity() {
        return new Branch(id, address, openedDate);
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getOpenedDate() {
        return openedDate;
    }

    public void setOpenedDate(LocalDate openedDate) {
        this.openedDate = openedDate;
    }
}
