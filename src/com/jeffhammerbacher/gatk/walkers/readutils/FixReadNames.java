package com.jeffhammerbacher.gatk.walkers.readutils;

import com.sun.tools.doclets.internal.toolkit.util.DocFinder;
import net.sf.samtools.SAMFileWriter;
import org.broadinstitute.sting.commandline.Output;
import org.broadinstitute.sting.gatk.contexts.ReferenceContext;
import org.broadinstitute.sting.gatk.refdata.RefMetaDataTracker;
import org.broadinstitute.sting.gatk.walkers.ReadWalker;
import org.broadinstitute.sting.utils.sam.GATKSAMRecord;

public class FixReadNames extends ReadWalker<Integer, Integer> {
    @Output
    SAMFileWriter out;

    @Override
    public Integer map(ReferenceContext referenceContext, GATKSAMRecord gatksamRecord, RefMetaDataTracker refMetaDataTracker) {
        gatksamRecord.setReadName(gatksamRecord.getReadGroup().getPlatformUnit() + "." + gatksamRecord.getReadName());
        out.addAlignment(gatksamRecord);
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
