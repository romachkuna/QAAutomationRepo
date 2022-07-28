public abstract class BasePage {

    BaseElement uniqueElement;
    String name;

    public BasePage(BaseElement uniqueElement,String name){
        this.uniqueElement = uniqueElement;
        this.name = name;
    }


}
