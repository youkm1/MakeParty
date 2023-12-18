import static org.junit.jupiter.api.Assertions.*;

public void testGetPageHierarchyAsXml() throws Exception{

    //SO MANY ADDPAGE FUNCTION
    crawler.addPage(root, PathParser.parse("PageOne"));
    crawler.addPage(root, PathParser.parse("PageOne.ChildOne"));
    crawler.addPage(root, PathParser.parse("PageTwo"));

    request.setResoure("root");

}