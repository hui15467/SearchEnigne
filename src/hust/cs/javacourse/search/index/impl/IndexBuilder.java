package hust.cs.javacourse.search.index.impl;
import hust.cs.javacourse.search.index.AbstractDocument;
import hust.cs.javacourse.search.index.AbstractDocumentBuilder;
import hust.cs.javacourse.search.index.AbstractIndex;
import hust.cs.javacourse.search.index.AbstractIndexBuilder;
import hust.cs.javacourse.search.util.Config;
import hust.cs.javacourse.search.util.FileUtil;

import java.io.File;
/*
* <pre>
 * IndexBuilder完成索引构造的工作
 * </pre>*/
public class IndexBuilder extends AbstractIndexBuilder {
    public IndexBuilder(AbstractDocumentBuilder docBuilder){
        super(docBuilder);
    }
/*
* <pre>
*     z指定目录下的所有文件的倒排索引
*     需要遍历解析目录下的每一个文件，得到对应的Document对象
*     再一次加入到索引，并将其保存到文件
*     rootDirectory:指定目录
*     返回构建好的索引
*     </pre>*/
    @Override
    public AbstractIndex buildIndex(String rootDirectory) {
        AbstractIndex index=new Index();
        for(String path:FileUtil.list(rootDirectory)){
            AbstractDocument doc=this.docBuilder.build(docId++,path,new File(path));
            index.addDocument(doc);
        }
        index.save(new File(Config.INDEX_DIR+"index.dat"));
        return index;
    }
}
