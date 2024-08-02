import React, { Component } from "react";
import { useState } from "react";
import axios from "axios";

const CmntInputComponent = ({apprId, userId}) => {
    // use hook 'useState' to manage cmnt
    const [cmnt, setCmnt] = useState('');
    const [isSubmitted, setIsSubmitted] = useState(false); 

    const handleCmntInput = (event) => {
        setCmnt(event.target.value);
    }

    const handleCmntSubmit = async (event) => {
        event.preventDefault();
        try{
            const response = await axios.put(`/api/appr/check?apprId=${apprId}&userId=${userId}&cmnt=${cmnt}&apprProc=${2}`,{data:cmnt});
 
            setIsSubmitted(true);
            console.log('done',response.data);
        }
        catch(error){
            console.error('error', error)
        }
        // fetch(`/api/appr/check?apprId=${apprId}&userId=${userId}&cmnt=${cmnt}&apprProc=${'2'}`)
    }

    return(
        <form onSubmit={handleCmntSubmit}>
            <label>
                코멘트
                <input
                type="text"
                value={cmnt}
                onChange={handleCmntInput}
                disabled={isSubmitted}>
                </input>
            </label>
            <button type="submit" disabled={isSubmitted}>승인</button>
            {/* <button>반려</button> */}
        </form>
    );
};

export default CmntInputComponent;