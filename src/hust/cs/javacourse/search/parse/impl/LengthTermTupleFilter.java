package hust.cs.javacourse.search.parse.impl;
import hust.cs.javacourse.search.index.AbstractTermTuple;
import hust.cs.javacourse.search.parse.AbstractTermTupleFilter;
import hust.cs.javacourse.search.parse.AbstractTermTupleStream;
import hust.cs.javacourse.search.util.Config;

/*<pre>
* 功能是过滤长度过长或过短的Term
* </pre>*/
public class LengthTermTupleFilter extends AbstractTermTupleFilter{
    /**
     * 构造函数
     * @param input AbstractTermTupleStream
     */
    public LengthTermTupleFilter(AbstractTermTupleStream input) {
        super(input);
    }
    /*获得下一个三元组，
     * 过滤‘如果到了流末尾，返回null*/

    @Override
    public AbstractTermTuple next() {
        AbstractTermTuple tuple=input.next();
        if(tuple==null) return null;
        while(tuple.term.getContent().length()>Config.TERM_FILTER_MAXLENGTH||tuple.term.getContent().length()<Config.TERM_FILTER_MINLENGTH){
            tuple=input.next();
            if(tuple==null) return null;
        }return tuple;
    }
}
