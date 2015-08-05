package com.example.fabrice.joetz2.Helpers.SaripaarRules;

import com.example.fabrice.joetz2.Helpers.SaripaarRules.Rrn;
import com.mobsandgeeks.saripaar.AnnotationRule;

/**
 * Created by Fabrice on 4/08/2015.
 */
public class RrnRule extends AnnotationRule<Rrn, String> {

    protected RrnRule(Rrn rrn) {
        super(rrn);
    }

    @Override
    public boolean isValid(String data) {

        if (data.length() == 11) {
            int rrnNumber = Integer.parseInt(data.substring(0, 9));
            int modulo = rrnNumber % 97;
            int checkSum = Integer.parseInt(data.substring(9, 11));
            return (Integer.compare(modulo, 97 - checkSum) == 0);
        } else
            return false;
    }
}
