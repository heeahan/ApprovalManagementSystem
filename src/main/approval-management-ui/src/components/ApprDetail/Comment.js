import React, { Component } from "react";
import { useState } from "react";
import axios from "axios";

const CmntInputComponent = ({ apprId, userId, apprDiv }) => {
    // use hook 'useState' to manage cmnt
    const [cmnt, setCmnt] = useState('');
    const [isSubmitted, setIsSubmitted] = useState(false);

    const handleCmntInput = (event) => {
        setCmnt(event.target.value);
    }

    const handleCmntSubmit = async (event) => {
        event.preventDefault();
        try {

            /* //A basic implement but stupid//
            if (apprDiv === 'B' || apprDiv === 'D') {
                const response = await axios.put(`/api/appr/check?apprId=${apprId}&userId=${userId}&cmnt=${cmnt}&apprProc=${2}`, { data: cmnt });
                setIsSubmitted(true);
                console.log('done', response.data);
            }
            else if (apprDiv === 'C') {
                const response = await axios.put(`/api/appr/check?apprId=${apprId}&userId=${userId}&cmnt=${cmnt}&apprProc=${4}`, { data: cmnt });
                setIsSubmitted(true);
                console.log('done', response.data);
            */

            let apprProc;
            if (apprDiv === 'B' || apprDiv === 'D') {
                apprProc = 2;
            } else if (apprDiv === 'C') {
                apprProc = 4;
            }

            if (apprProc) {
                const response = await axios.put(`/api/appr/check?apprId=${apprId}&userId=${userId}&cmnt=${cmnt}&apprProc=${apprProc}`, { data: cmnt });
                setIsSubmitted(true);
                console.log('done', response.data);
            }
        }
        catch (error) {
            console.error('error', error)
        }
    };

    const handleReject = async (event) => {
        event.preventDefault();
        try {
            let apprProc;
            if (apprDiv === 'B' || apprDiv === 'D') {
                apprProc = 3;
            } else if (apprDiv === 'C') {
                apprProc = 5;
            }

            if (apprProc) {
                const response = await axios.put(`/api/appr/check?apprId=${apprId}&userId=${userId}&cmnt=${cmnt}&apprProc=${apprProc}`, { data: cmnt });
                setIsSubmitted(true);
                console.log('done', response.data);
            }
        }
        catch (error){
            console.error('error', error)
        }
    };

    return (
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
            <button type="button" onClick={handleReject} disabled={isSubmitted}>반려</button>
        </form>
    );
};

export default CmntInputComponent;