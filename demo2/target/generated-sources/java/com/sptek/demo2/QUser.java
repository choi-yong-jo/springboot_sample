package com.sptek.demo2;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 1854830216L;

    public static final QUser user = new QUser("user");

    public final StringPath email = createString("email");

    public final StringPath id = createString("id");

    public final DateTimePath<java.util.Date> inDate = createDateTime("inDate", java.util.Date.class);

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final DateTimePath<java.util.Date> upDate = createDateTime("upDate", java.util.Date.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

