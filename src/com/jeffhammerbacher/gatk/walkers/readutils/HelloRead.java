package com.jeffhammerbacher.gatk.walkers.readutils;

import org.broadinstitute.sting.commandline.Output;
import org.broadinstitute.sting.gatk.contexts.ReferenceContext;
import org.broadinstitute.sting.gatk.refdata.RefMetaDataTracker;
import org.broadinstitute.sting.gatk.walkers.ReadWalker;

import java.io.PrintStream;

public class HelloRead extends ReadWalker<Integer, Integer> {
    @Output
    public PrintStream out;

    @Override
    public Integer map(ReferenceContext referenceContext, org.broadinstitute.sting.utils.sam.GATKSAMRecord gatksamRecord, RefMetaDataTracker refMetaDataTracker) {
        out.println("Hello, " + gatksamRecord.getReadString() +
                       " at " + referenceContext.getLocus());
        return null;
    }

    @Override
    public Integer reduceInit() {
        return null;
    }

    @Override
    public Integer reduce(Integer integer, Integer integer2) {
        return null;
    }
}
