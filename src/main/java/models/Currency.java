package models;

public class Currency
{
    private final IDToken token;
    private final String name;
    private final long count;


    public Currency(IDToken token, String name, long count)
    {
        this.token = token;
        this.name = name;
        this.count = count;
    }

    public IDToken getToken() {return token;}

    public String getName() {return name;}

    public long getCount() {return count;}
}