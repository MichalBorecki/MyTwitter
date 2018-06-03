package pl.coderslab.mytwitter.entity;

import java.util.List;

public class Response {
	private String status;
	private List<Comment> comentsList;

	public Response() {

	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Comment> getComentsList() {
		return comentsList;
	}

	@Override
	public String toString() {
		return String.format("Response [status=%s, comentsList=%s]", status, comentsList);
	}

	public void setComentsList(List<Comment> comentsList) {
		this.comentsList = comentsList;
	}

	public Response(String status, List<Comment> comentsList) {
		super();
		this.status = status;
		this.comentsList = comentsList;
	}
}