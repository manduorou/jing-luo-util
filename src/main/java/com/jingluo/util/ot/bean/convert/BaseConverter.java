//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jingluo.util.ot.bean.convert;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

public abstract class BaseConverter<S, D> extends BidirectionalConverter<S, D> {
    public BaseConverter() {
    }

    public abstract String convertName();

    public abstract D convertSourceToTarget(S var1);

    public abstract S convertTargetToSource(D var1);

    public D convertTo(S source, Type<D> destinationType, MappingContext mappingContext) {
        return this.convertSourceToTarget(source);
    }

    public S convertFrom(D source, Type<S> destinationType, MappingContext mappingContext) {
        return this.convertTargetToSource(source);
    }
}
