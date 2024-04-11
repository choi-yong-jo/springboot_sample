package com.sptek.demo2;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = 1647492041L;

    public static final QBoard board = new QBoard("board");

    public final NumberPath<Integer> bno = createNumber("bno", Integer.class);

    public final StringPath content = createString("content");

    public final DateTimePath<java.util.Date> inDate = createDateTime("inDate", java.util.Date.class);

    public final StringPath title = createString("title");

    public final DateTimePath<java.util.Date> upDate = createDateTime("upDate", java.util.Date.class);

    public final NumberPath<Integer> viewCnt = createNumber("viewCnt", Integer.class);

    public final StringPath writer = createString("writer");

    public QBoard(String variable) {
        super(Board.class, forVariable(variable));
    }

    public QBoard(Path<? extends Board> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoard(PathMetadata metadata) {
        super(Board.class, metadata);
    }

}

