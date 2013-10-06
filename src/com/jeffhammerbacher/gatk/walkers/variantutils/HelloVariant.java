package com.jeffhammerbacher.gatk.walkers.variantutils;

import org.broadinstitute.sting.commandline.Input;
import org.broadinstitute.sting.commandline.Output;
import org.broadinstitute.sting.commandline.RodBinding;
import org.broadinstitute.sting.gatk.contexts.AlignmentContext;
import org.broadinstitute.sting.gatk.contexts.ReferenceContext;
import org.broadinstitute.sting.gatk.refdata.RefMetaDataTracker;
import org.broadinstitute.sting.gatk.walkers.RodWalker;
import org.broadinstitute.variant.variantcontext.VariantContext;

import java.io.PrintStream;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class HelloVariant extends RodWalker<Integer, Integer> {
    @Input(fullName="vcf", shortName = "V", doc="Input VCF file(s)", required=false)
    public List<RodBinding<VariantContext>> rods = Collections.emptyList();

    @Output
    public PrintStream out;

    @Override
    public Integer map(RefMetaDataTracker refMetaDataTracker, ReferenceContext referenceContext, AlignmentContext alignmentContext) {
        if (refMetaDataTracker != null) {
            Collection<VariantContext> vcs = refMetaDataTracker.getValues(VariantContext.class, alignmentContext.getLocation());

            for (VariantContext vc : vcs) {
                out.println("Hello, ref=" + vc.getReference() +
                                  ",alt=" + vc.getAltAlleleWithHighestAlleleCount() +
                                   " at " + vc.getChr() +
                                      ":" + vc.getStart()
                );
            }
        }

        return 1;
    }

    @Override
    public Integer reduceInit() {
        return 0;
    }

    @Override
    public Integer reduce(Integer value, Integer sum) {
        return value + sum;
    }

    public void onTraversalDone(Integer sum) {
        out.println("Said hello to " + sum + " records.");
    }
}
