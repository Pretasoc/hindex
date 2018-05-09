package realAufgabe1;

public class PaperCitation {
	
	private String author;
	private String title;
	private int citations;

	PaperCitation(String author, String title, int citations){
		this.setAuthor(author);
		this.setTitle(title);
		this.setCitations(citations);
	}

	public int getCitations() {
		return citations;
	}

	public void setCitations(int citation) {
		this.citations = citation;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}
