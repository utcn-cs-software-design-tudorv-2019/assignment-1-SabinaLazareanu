package assigment1.tucn.cs.DAL;

public class User {

	private Long idUser;
	private String name;
	private String address;
	private String PNC;
	private String ICN;

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPNC() {
		return PNC;
	}

	public void setPNC(String pNC) {
		PNC = pNC;
	}

	public String getICN() {
		return ICN;
	}

	public void setICN(String iCN) {
		ICN = iCN;
	}

	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", name=" + name + ", address=" + address + ", PNC=" + PNC + ", ICN=" + ICN
				+ "]";
	}

}
