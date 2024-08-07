import React, { Component, useEffect } from "react";
import { Link, useParams, useNavigate } from 'react-router-dom';
import { useState } from "react";
import axios from "axios";
import './Comment.css'

const CmntInputComponent = ({ apprId, userId, apprDiv }) => {
    // use hook 'useState' to manage cmnt
    const [cmnt, setCmnt] = useState('');
    const [isSubmitted, setIsSubmitted] = useState(false);
    const [isApproveOpen, setIsApproveOpen] = useState(false); // State to manage popup visibility
    const [isRejectOpen, setIsRejectOpen] = useState(false); // State for reject popup visibility
    const navigate = useNavigate();

    useEffect(() => {
        const submitted = localStorage.getItem('isSubmitted');
        if (submitted === 'true') {
            setIsSubmitted(true);
        }
    }, []);



    const handleCmntInput = (event) => {
        setCmnt(event.target.value);
    }

    const togglePopup = () => {
        setIsApproveOpen(!isApproveOpen); // Toggle the popup visibility
    };

    const toggleRejPopup = () => {
        setIsRejectOpen(!isRejectOpen); // Toggle the Rej popup visibility
    };

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
                // setIsApproveOpen(true);


                // localStorage.setItem('isSubmitted', 'true'); // *to make the input not active anymore* At the begining tried this but it will do the same thing to every user
                // even not inputed yet
                // togglePopup();
                // console.log('done', response.data);
            }
        }

        catch (error) {
            console.error('error', error)
        }
    };

    const handleReject = async (event) => {
        event.preventDefault();
        // if (cmnt.trim() === '') {
        //     return;
        // } // if the input is empty, do not submit
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
                setIsRejectOpen(true);
                // localStorage.setItem('isSubmitted', 'true');
                // console.log('done', response.data);
            }
        }
        catch (error) {
            console.error('error', error)
        }
    };

    return (
        <div>
            {apprDiv !== 'E' && (

                <form id="cmnt-form" onSubmit={handleCmntSubmit}>
                    <label>
                        코멘트:
                        <textarea
                            id="cmnt"
                            type="text"
                            value={cmnt}
                            onChange={handleCmntInput}
                            disabled={isSubmitted}>
                        </textarea>
                    </label>
                    <div className="button-container">
                        <button
                            id="approve-button"
                            type="submit"
                            disabled={isSubmitted}
                            onClick={togglePopup}>
                            승인
                        </button>
                        {isApproveOpen && (
                            <div className="appr-popup">
                                <div className="appr-popup-inner">
                                    <h2>승인 완료</h2>
                                    <p>승인이 성공적으로 완료되었습니다!</p>
                                    {/* <button id="popup-close-button" onClick={togglePopup}>닫기</button> */}
                                    <button id="popup-close-button" onClick={() => navigate(-1)}>닫기</button>
                                </div>
                            </div>
                        )}
                        <button id="reject-button"
                            type="button" onClick={handleReject}
                            disabled={isSubmitted}>
                            반려
                        </button>
                        {isRejectOpen && (
                            <div className="rej-popup">
                                <div className="rej-popup-inner">
                                    <h2>반려 완료</h2>
                                    <p>반려가 성공적으로 완료되었습니다!</p>
                                    {/* <button id="popup-close-button" onClick={toggleRejPopup}>닫기</button> */}
                                    <button id="popup-close-button" onClick={() => navigate(-1)}>닫기</button>
                                </div>
                            </div>
                        )}
                    </div>
                </form>
            )}
        </div>
    );
};

export default CmntInputComponent;