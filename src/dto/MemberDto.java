package dto;

import entity.Member;

public class MemberDto {
    private int id;
    private String name;
    private String email;
    private String phoneNumber;

    public MemberDto(int id, String name, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Member toEntity() {
        return new Member(id, name, email, phoneNumber);
    }
}
