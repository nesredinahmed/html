import java.util.ArrayList;
interface printable{
    public String toString();
}
class Attr{
    private String key;
    private String value;
    Attr(String key, String value){
        this.key = key;
        this.value=value;
    }
    @Override
    public String toString() {
        return String.format("%s=\"%s\"", key, value);
    }
}
class Tag implements printable{
    private String name;
    ArrayList<Attr> attrs;
    String content;
    Tag(String name, ArrayList<Attr> attrs, String content){
        this.name = name;
        this.attrs = attrs;
        this.content = content;
    }
    @Override
    public String toString() {
        return String.format("<%s%s>%s</%s>", name, getAttrs(), content, name);
    }
    private String getAttrs(){
        if(attrs == null){
           return "";
        }
        ArrayList<String> list = new ArrayList<>();
        for(Attr a : attrs){
            list.add(a.toString());
        }
        return " " + String.join(" ", list);
    }
}
class Div extends Tag{
    Div(ArrayList<Attr> attrs, String content){
        super("DIV", attrs, content);
    }
}
class Strong extends Tag{
    Strong(String content){
        super("B", null, content);
    }
}
class Warning{
    Div div;
    Warning(String content){
        ArrayList<Attr> attrs = new ArrayList<Attr>();
        attrs.add(new Attr("id", "123"));
        attrs.add(new Attr("class", "box warning"));

        this.div = new Div(attrs, "WARNING: " + new Strong(content));
    }

    public String toString() {
        return div.toString();
    }
}
public class HTML {
    public static void main(String[] args) {
        System.out.println(new Warning("enough"));
//        System.out.println(new Div(attrs, String.format("That is %s!", enough )));
//        System.out.println(new Warning("This is a warning!!!"));
    }
}

/*
<div id="123" class="box warning">
    That is <b>enough</b>!
</div>
 */