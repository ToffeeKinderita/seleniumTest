import org.openqa.selenium.By;

public class ByVar {
    private static final By ID = By.id("photo");
    private static final By CLASSNAME = By.className("submenu");
    private static final By TAGNAME = By.tagName("ul");
    private static final By NAME = By.name("SearchQuery");
    private static final By LINKTEXT = By.linkText("Workspace at Zetkin 500");
    private static final By PARTIALLINKTEXT = By.partialLinkText("Zetkin");
    private static final By CSS = By.cssSelector("#info div");
    private static final By XPATH = By.xpath("//div[@id='photo']");

}
