import React, { Component } from "react";
import { useState } from "react";

const CmntInputComponent = ({apprId, userId}) => {
    // use hook 'useState' to manage cmnt
    const [cmnt, setCmnt] = useState('');

    const handleCmntInput = (event) => {
        setCmnt(event.target.value);
    }

    const handleCmntSubmit = (event) => {
        event.prevenDefault();
        fetch(`/api/appr/check?apprId=${apprId}&userId=${userId}&cmnt=${cmnt}&apprProc=${'2'}`)
    }

    return(
        <form onSubmit={handleCmntSubmit}>
            <label>
                코멘트
                <input
                type="text"
                value={cmnt}
                onChange={handleCmntInput}>
                </input>
            </label>
            <button type="submit">승인</button>
        </form>
    );
};

export default CmntInputComponent;