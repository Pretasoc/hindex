package realAufgabe1;

public class PaperCitation {
	
	private String author;
	private String title;
	private int citations;

	public PaperCitation(String author, String title, int citations){
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
	
	@Override
	public boolean equals(Object o) {
		PaperCitation other = (PaperCitation)o;
		return this.author.equals(other.getAuthor()) && this.title.equals(other.getTitle()) && this.citations == other.getCitations();
	}

}
