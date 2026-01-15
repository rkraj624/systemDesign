package dsa.practice.lld.paymentGateway.model;

public class UserDto {
    String id;
    String name;
    String email;

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private String id;
        private String name;
        private String email;

        public Builder id(String id){
            this.id = id;
            return this;
        }
        public Builder name(String name){
            this.name = name;
            return this;
        }
        public Builder email(String email){
            this.email = email;
            return this;
        }
        public UserDto build(){
            UserDto userDto = new UserDto();
            userDto.id = this.id;
            userDto.name = this.name;
            userDto.email = this.email;
            return userDto;
        }
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
